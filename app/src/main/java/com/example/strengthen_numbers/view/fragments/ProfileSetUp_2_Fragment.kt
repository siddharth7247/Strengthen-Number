package com.example.strengthen_numbers.view.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.example.strengthen_numbers.R
import com.example.strengthen_numbers.R.layout.gender_bottom_sheet
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText


class ProfileSetUp_2_Fragment : Fragment() {

    lateinit var edtGender : TextInputEditText
    lateinit var genderBottomSheet : View
    lateinit var imageSourceBottomSheet : View
    lateinit var btnMale : MaterialButton
    lateinit var btnFemale : MaterialButton
    lateinit var btnOther : MaterialButton
    lateinit var btnCamera : MaterialButton
    lateinit var btnGallery : MaterialButton
    lateinit var profileImage : ImageView
    lateinit var gallaryLauncher : ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        gallaryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()){
            val imgUrl = it
            profileImage.setImageURI(imgUrl)
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_set_up_2_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        edtGender = view.findViewById(R.id.edtGender)
        profileImage = view.findViewById(R.id.profile_image)

        edtGender.setOnClickListener{
           getGender()
        }
        profileImage.setOnClickListener{
           getProfileImage()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 200){
            val image = data?.extras!!["data"] as Bitmap
            profileImage.setImageBitmap(image)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        if (view != null) {
            val parentViewGroup = requireView().parent as ViewGroup?
            parentViewGroup?.removeAllViews();
        }
    }

    private fun getGender(){
        genderBottomSheet = layoutInflater.inflate(R.layout.gender_bottom_sheet,null)
        btnMale = genderBottomSheet.findViewById(R.id.btnMale)
        btnFemale = genderBottomSheet.findViewById(R.id.btnFemale)
        btnOther = genderBottomSheet.findViewById(R.id.btnOther)
        val genderDialog = BottomSheetDialog(requireContext())
        genderDialog.setContentView(genderBottomSheet)
        genderDialog.show()
        btnMale.setOnClickListener{
            edtGender.setText(btnMale.text)
            genderDialog.dismiss()
        }
        btnFemale.setOnClickListener{
            edtGender.setText(btnFemale.text)
            genderDialog.dismiss()
        }
        btnOther.setOnClickListener{
            edtGender.setText(btnOther.text)
            genderDialog.dismiss()
        }
    }
    private fun getProfileImage(){
        imageSourceBottomSheet = layoutInflater.inflate(R.layout.image_source_bottom_sheet,null)
        btnCamera = imageSourceBottomSheet.findViewById(R.id.btnCamera)
        btnGallery = imageSourceBottomSheet.findViewById(R.id.btnGallary)
        val imageSourceDialog = BottomSheetDialog(requireContext())
        imageSourceDialog.setContentView(imageSourceBottomSheet)

        imageSourceDialog.show()
        btnCamera.setOnClickListener{
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent,200)
            imageSourceDialog.dismiss()
        }
        btnGallery.setOnClickListener{
            gallaryLauncher.launch("image/*")
            imageSourceDialog.dismiss()
        }
    }
}
