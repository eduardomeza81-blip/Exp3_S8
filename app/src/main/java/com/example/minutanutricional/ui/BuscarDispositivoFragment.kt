package com.example.minutanutricional.ui

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.minutanutricional.R
import com.google.android.gms.location.LocationServices
import com.example.minutanutricional.services.LocationService
import android.content.Intent
import android.net.Uri

class BuscarDispositivoFragment : Fragment() {

    private val LOCATION_PERMISSION_CODE = 1001
    private var lastLat: Double? = null
    private var lastLon: Double? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_buscar_dispositivo, container, false)
        val btnLimpiar = view.findViewById<Button>(R.id.btnLimpiar)
        val btnBuscar = view.findViewById<Button>(R.id.btnBuscar)
        val txtUbicacion = view.findViewById<TextView>(R.id.txtUbicacion)
        val txtHistorial = view.findViewById<TextView>(R.id.txtHistorial)
        val btnMaps = view.findViewById<Button>(R.id.btnMaps)

        setMapsEnabled(btnMaps, false)

        val fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())
        fun cargarHistorial() {
            LocationService.getMyLocations(
                limit = 5,
                onSuccess = { list ->
                    val sb = StringBuilder("Historial:\n")
                    list.forEachIndexed { i, item ->
                        val lat = item["lat"]
                        val lon = item["lon"]
                        val timestamp = item["timestamp"] as? com.google.firebase.Timestamp
                        val formattedDate = if (timestamp != null) {
                            val date = timestamp.toDate()
                            val sdf = java.text.SimpleDateFormat("dd/MM/yyyy HH:mm", java.util.Locale.getDefault())
                            sdf.format(date)
                        } else {
                            "Sin fecha"
                        }
                        sb.append("${i + 1}) 📍 $formattedDate\n")
                        sb.append("   lat=$lat\n")
                        sb.append("   lon=$lon\n")
                        sb.append("----------------------\n\n")
                    }
                    txtHistorial.text = sb.toString()
                },
                onError = { msg ->
                    txtHistorial.text = "Historial:\n❌ $msg"
                }
            )
        }
        btnBuscar.setOnClickListener {

            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_CODE
                )
                return@setOnClickListener
            }

            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val lat = location.latitude
                    val lon = location.longitude
                    lastLat = lat
                    lastLon = lon

                    txtUbicacion.text = "Lat: $lat\nLon: $lon\nGuardando en Firebase..."
                    setMapsEnabled(btnMaps, true)
                    LocationService.saveLocation(
                        lat = lat,
                        lon = lon,
                        onSuccess = {
                            txtUbicacion.append("\n✅ Guardado en Firestore")
                            cargarHistorial()
                        },
                        onError = { msg ->
                            txtUbicacion.append("\n❌ Error Firestore: $msg")
                        }
                    )
                } else {
                    txtUbicacion.text = "No se pudo obtener ubicación (prueba con GPS encendido)"
                    setMapsEnabled(btnMaps, false)
                    lastLat = null
                    lastLon = null
                }
            }

        }

        cargarHistorial()
        btnMaps.setOnClickListener {
            val lat = lastLat
            val lon = lastLon

            if (lat != null && lon != null) {
                abrirEnMaps(lat, lon)
            } else {
                txtUbicacion.append("\n📍 Primero presiona 'OBTENER UBICACIÓN'")
            }
        }
        btnLimpiar.setOnClickListener {
            txtHistorial.text = "Historial:\n(Limpiando...)"
            setMapsEnabled(btnMaps, false)
            LocationService.clearMyLocations(
                onSuccess = {
                    txtHistorial.text = "Historial:\n✅ Historial eliminado"
                    lastLat = null
                    lastLon = null
                },
                onError = { msg ->
                    txtHistorial.text = "Historial:\n(sin registros)"
                }
            )
        }
        return view
    }
    private fun abrirEnMaps(lat: Double, lon: Double) {
        val uri = android.net.Uri.parse(
            "https://www.google.com/maps/search/?api=1&query=$lat,$lon"
        )
        val intent = android.content.Intent(android.content.Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
    private fun setMapsEnabled(btnMaps: Button, enabled: Boolean) {
        btnMaps.isEnabled = enabled
        btnMaps.isClickable = enabled
        btnMaps.alpha = if (enabled) 1.0f else 0.45f
    }
}