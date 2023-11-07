package com.efesen.bottomnavigationviewwithimagevew

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import com.efesen.bottomnavigationviewwithimagevew.databinding.ActivityMainBinding
import com.efesen.bottomnavigationviewwithimagevew.view.ActivityFragment
import com.efesen.bottomnavigationviewwithimagevew.view.AppointmenFragment
import com.efesen.bottomnavigationviewwithimagevew.view.BookmarkFragment
import com.efesen.bottomnavigationviewwithimagevew.view.HomeFragment
import com.efesen.bottomnavigationviewwithimagevew.view.ProfileFragment
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //Views
    private lateinit var homeFragmentButton: AppCompatImageButton
    private lateinit var activityFragmentButton: AppCompatImageButton
    private lateinit var bookmarkFragmentButton: AppCompatImageButton
    private lateinit var appointmentFragmentButton: AppCompatImageButton
    private lateinit var profileImageView: CircleImageView

    //Fragments
    private var homeFragment: HomeFragment? = null
    private var appointmentFragment: AppointmenFragment? = null
    private var bookmarkFragment: BookmarkFragment? = null
    private var profileFragment: ProfileFragment? = null
    private var activityFragment: ActivityFragment? = null

    private val fragmentManager = supportFragmentManager

    private var newColor = Color.parseColor("#F86F03")
    private var oldColor = Color.parseColor("#000000")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindUIComponent()

        changeButtonIconColor(homeFragmentButton, newColor)
        selectedFeedFragmentByDefault()
        profileImageView.setImageResource(R.drawable.profile_photo)

        handleClickListeners()

    }

    private fun bindUIComponent() {

        homeFragmentButton = binding.homeButton
        activityFragmentButton = binding.activityButton
        bookmarkFragmentButton = binding.bookmarkButton
        appointmentFragmentButton = binding.appointmentButton
        profileImageView = binding.profilePhoto

        homeFragment = HomeFragment()
        appointmentFragment = AppointmenFragment()
        bookmarkFragment = BookmarkFragment()
        profileFragment = ProfileFragment()
        activityFragment = ActivityFragment()
    }

    private fun changeButtonIconColor(button: AppCompatImageButton, newColor: Int) {

        // get the current icon of the button
        val originalIcon = button.drawable
        // Make a copy (to avoid changing the original)
        val coloredIcon = originalIcon.mutate()
        // set NewColor
        coloredIcon.setTint(newColor)
        button.setImageDrawable(coloredIcon)
    }

    private fun selectedFeedFragmentByDefault() {
        fragmentManager.beginTransaction()
            // Add first fragment to container
            .add(R.id.container, homeFragment!!)
            // Keep copy of it in memory to avoid re creating it (it allows to return 1 step back)
            .addToBackStack(null)
            // Show to user
            .commit()
    }

    private fun replaceFragment(fragment: Fragment?) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment!!)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun resetButtonColors() {
        changeButtonIconColor(homeFragmentButton, oldColor)
        changeButtonIconColor(activityFragmentButton, oldColor)
        changeButtonIconColor(bookmarkFragmentButton, oldColor)
        changeButtonIconColor(appointmentFragmentButton, oldColor)
        profileImageView.setBackgroundResource(0)
    }

    private fun handleClickListeners() {
        homeFragmentButton.setOnClickListener {
            resetButtonColors()
            changeButtonIconColor(homeFragmentButton, newColor)
            // Render HomeFragment
            replaceFragment(homeFragment)
        }
        activityFragmentButton.setOnClickListener {
            resetButtonColors()
            changeButtonIconColor(activityFragmentButton, newColor)
            // Render ActivityFragment
            replaceFragment(activityFragment)
        }
        bookmarkFragmentButton.setOnClickListener {
            resetButtonColors()
            changeButtonIconColor(bookmarkFragmentButton, newColor)
            // Render BookmarkFragment
            replaceFragment(bookmarkFragment)
        }
        appointmentFragmentButton.setOnClickListener {
            resetButtonColors()
            changeButtonIconColor(appointmentFragmentButton, newColor)
            changeButtonIconColor(bookmarkFragmentButton, oldColor)
            // Render AppointmentFragment
            replaceFragment(appointmentFragment)
        }
        profileImageView.setOnClickListener {
            resetButtonColors()
            profileImageView.setBackgroundResource(R.drawable.cirle_view)
            changeButtonIconColor(appointmentFragmentButton, oldColor)
            // Render ProfileFragment
            replaceFragment(appointmentFragment)
        }
    }

}