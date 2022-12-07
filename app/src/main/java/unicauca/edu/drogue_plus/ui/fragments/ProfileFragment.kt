package unicauca.edu.drogue_plus.ui.fragments

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import unicauca.edu.drogue_plus.CAMERA_PERMISSION
import unicauca.edu.drogue_plus.R
import unicauca.edu.drogue_plus.TAKE_PICTURE
import unicauca.edu.drogue_plus.checkPermission
import unicauca.edu.drogue_plus.data.viewmodels.LoginViewModel
import unicauca.edu.drogue_plus.databinding.FragmentLocationBinding
import unicauca.edu.drogue_plus.databinding.FragmentProfileBinding
import unicauca.edu.drogue_plus.ui.activities.LoginActivity
import java.util.jar.Manifest

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding get() = _binding!!

    private val loginViewModel: LoginViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.profileFragmentLogout.setOnClickListener {
            loginViewModel.logOut()
        }

        binding.profileFragmentImage.setOnClickListener {
            if (this.checkPermission(android.Manifest.permission.CAMERA, CAMERA_PERMISSION)) {
                openCamera()
            }
        }

        observeViewModels()

    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openCamera()
        } else {
            Snackbar.make(binding.root, "Permiso no otorgado", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TAKE_PICTURE) {
            if (data != null && data.extras != null) {
                //Otra forma de verificar nulidad
                // val extras = data!!.extras!!
                val extras = data.extras!!
                val image = extras["data"] as Bitmap?
                binding.profileFragmentImage.setImageBitmap(image)
            }

        }
    }

    private fun observeViewModels() {
        loginViewModel.user.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.profileFragmentName.text = it.nombre
                binding.profileFragmentEmail.text = it.email
                binding.profileFragmentGender.text = it.genero
                if (it.image != null) {
                    Glide.with(binding.root).load(it.image).centerCrop()
                        .into(binding.profileFragmentImage)
                }
            }else{
                val intent = Intent(requireContext(), LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                requireActivity().finish()
            }
        })
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        try {
            startActivityForResult(intent, TAKE_PICTURE)

        } catch (e: ActivityNotFoundException) {

        }
    }

}