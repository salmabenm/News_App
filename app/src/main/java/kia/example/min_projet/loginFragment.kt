package kia.example.min_projet


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

import kia.example.min_projet.databinding.FragmentLoginBinding


class loginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =FragmentLoginBinding.inflate(inflater, container, false)
        val view=binding.root
        
        var text=binding.textView
        text.setOnClickListener{
            val navController = findNavController()
            navController.navigate(R.id.action_loginFragment2_to_registerFragment2)
        }
        var email=binding.emailEt.text
        var pass=binding.passET.text
        var button=binding.button
        auth = FirebaseAuth.getInstance()
        button.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                if(TextUtils.isEmpty(email.toString())){
                    // Utilisation de requireContext() pour obtenir le contexte du fragment
                    Toast.makeText(requireContext(), "Entrez votre adresse e-mail", Toast.LENGTH_SHORT).show()
                    return
                }
                if(TextUtils.isEmpty(pass.toString())){
                    // Utilisation de requireContext() pour obtenir le contexte du fragment
                    Toast.makeText(requireContext(), "Entrez votre password", Toast.LENGTH_SHORT).show()
                    return
                }
                auth.signInWithEmailAndPassword(email.toString(), pass.toString())
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(requireContext(), MainActivity::class.java)
                            startActivity(intent)

                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()

                        }
                    }
 }

        })
        return binding.root
    }


}