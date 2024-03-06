package com.wigroup.composition2.presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wigroup.composition2.R
import com.wigroup.composition2.databinding.FragmentGameBinding
import com.wigroup.composition2.domain.entity.GameResult
import com.wigroup.composition2.domain.entity.GameSettings
import com.wigroup.composition2.domain.entity.Level

class GameFragment : Fragment() {

    private lateinit var level: Level

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvOption1.setOnClickListener {
            launchGameFinishedFragment(
                GameResult(
                    true,
                    0,
                    0,
                    GameSettings(
                        0,
                        0,
                        0,
                        0
                    ),
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelable(KEY_LEVEL, Level::class.java)?.let {
                level = it
            }
        } else {
            @Suppress("DEPRECATION")
            requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
                level = it
            }
        }
    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFinishedFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()
    }

    companion object {

        const val NAME = "GameFragment"
        private const val KEY_LEVEL = "level"

        fun newInstance(level: Level): GameFragment = GameFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY_LEVEL, level)
            }
        }
    }
}
