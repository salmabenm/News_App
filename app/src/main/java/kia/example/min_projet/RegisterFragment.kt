package kia.example.min_projet

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

import kia.example.min_projet.databinding.FragmentRegisterBinding


// ... (import statements)

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var myauth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        myauth = Firebase.auth
        val button = binding.button

        button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()
            val conf = binding.confirmPassEt.text.toString()

            if (TextUtils.isEmpty(email)) {
                showToast("Entrez votre adresse e-mail")
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(pass)) {
                showToast("Entrez votre mot de passe")
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(conf)) {
                showToast("Entrer la confirmation")
                return@setOnClickListener
            }

            if (pass != conf) {
                showToast("Les mots de passe ne correspondent pas")
                return@setOnClickListener
            }

            // Add email format validation here if needed

            // Firebase authentication
            myauth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val navController = findNavController()
                        navController.navigate(R.id.action_registerFragment2_to_loginFragment2)
                    } else {
                        showToast("Échec d'authentification: ${task.exception?.message}")
                        Log.e("AuthError", "Échec d'authentification: ${task.exception?.message}")
                    }
                }
        }

        binding.textView.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_registerFragment2_to_loginFragment2)
        }

        return binding.root
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
