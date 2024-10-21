package com.example.strengthen_numbers.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.example.strengthen_numbers.R
import com.example.strengthen_numbers.adapters.HobbyGridAdapter
import com.example.strengthen_numbers.models.IntrestModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText


class ProfileSetUp_3_Fragment : Fragment() {

    lateinit var hobbyGridView : GridView
    lateinit var hobbyList : List<IntrestModel>
    lateinit var intrestGrid : GridView
    lateinit var hobbyAdapter : HobbyGridAdapter
    lateinit var fitnessLevelBottlomSheet : View
    lateinit var btnBeginner : MaterialButton
    lateinit var btnIntermediate : MaterialButton
    lateinit var btnAdvance : MaterialButton
    lateinit var edtFitnessLevel : TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile_set_up_3_, container, false)
        intrestGrid = view.findViewById(R.id.hobbyGridView)
        edtFitnessLevel = view.findViewById(R.id.edtFitnessLevel)
        fitnessLevelBottlomSheet = inflater.inflate(R.layout.fitness_level_bottom_sheet,container)
        btnBeginner = fitnessLevelBottlomSheet.findViewById(R.id.btnBeginner)
        btnIntermediate = fitnessLevelBottlomSheet.findViewById(R.id.btnIntermedate)
        btnAdvance = fitnessLevelBottlomSheet.findViewById(R.id.btnAdvance)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hobbyGridView = view.findViewById(R.id.hobbyGridView)
        hobbyList = listOf(
            IntrestModel("Gym",R.drawable.ic_gym,R.drawable.ic_gym_white),
            IntrestModel("Yoga",R.drawable.ic_yoga,R.drawable.ic_yoga_white),
            IntrestModel("Cardio",R.drawable.ic_cardio,R.drawable.ic_cardio_white),
            IntrestModel("Workout",R.drawable.ic_home_workout,R.drawable.ic_workout_white),
            IntrestModel("Cycling",R.drawable.ic_cycling,R.drawable.ic_cycling_white),
            IntrestModel("Zumba",R.drawable.ic_zumba,R.drawable.ic_zumba_white),
            IntrestModel("Dieting",R.drawable.ic_diet,R.drawable.ic_diet_white),
            IntrestModel("Sport",R.drawable.ic_sports,R.drawable.ic_sports_white),
            IntrestModel("Running",R.drawable.ic_running,R.drawable.ic_running_white))

        hobbyAdapter = HobbyGridAdapter(hobbyList,requireContext())
        hobbyGridView.adapter = hobbyAdapter
        hobbyGridView.setOnItemClickListener { _, _, position, _ ->
            hobbyAdapter.toggleSelection(position)
        }
        edtFitnessLevel.setOnClickListener{
            val fitnessDialog = BottomSheetDialog(requireContext())
            fitnessDialog.setContentView(fitnessLevelBottlomSheet)
            fitnessDialog.show()
            btnBeginner.setOnClickListener{
                edtFitnessLevel.setText(btnBeginner.text)
                fitnessDialog.dismiss()
            }
            btnIntermediate.setOnClickListener{
                edtFitnessLevel.setText(btnIntermediate.text)
                fitnessDialog.dismiss()
            }
            btnAdvance.setOnClickListener{
                edtFitnessLevel.setText(btnAdvance.text)
                fitnessDialog.dismiss()
            }
        }
    }
    fun getInterestsInfo(): InterestsInfo {
        val selectedHobbies = hobbyAdapter.getSelectedHobbies()

        return InterestsInfo(
            interests = selectedHobbies.map { it.name },
        )
    }
}


data class InterestsInfo(
    val interests: List<String>,
)