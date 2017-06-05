package link.standen.michael.fatesheets.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import link.standen.michael.fatesheets.R;
import link.standen.michael.fatesheets.adapter.SkillArrayAdapter;
import link.standen.michael.fatesheets.view.AdapterLinearLayout;
import link.standen.michael.fatesheets.model.CoreCharacter;
import link.standen.michael.fatesheets.model.Skill;

/**
 * A fragment for managing a characters skills.
 */
public class CoreCharacterEditSkillsFragment extends CharacterEditAbstractFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.core_character_edit_skills, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// Skill
		Fragment childFragment = new SkillFragment();
		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
		transaction.replace(R.id.skill_container, childFragment).commit();
	}

	/**
	 * Class for managing skills.
	 */
	public static class SkillFragment extends CharacterEditAbstractFragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
								 Bundle savedInstanceState) {

			CoreCharacter character = getCoreCharacter();

			View rootView = inflater.inflate(R.layout.core_character_edit_skills_skill, container, false);

			// Skills
			final SkillArrayAdapter skillListAdapter = new SkillArrayAdapter(getContext(),
					R.layout.core_character_edit_skills_list_item, character.getSkills());
			((AdapterLinearLayout) rootView.findViewById(R.id.skills_list)).setAdapter(skillListAdapter);

			rootView.findViewById(R.id.add_skill).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					getCoreCharacter().getSkills().add(new Skill(null, ""));
					skillListAdapter.notifyDataSetChanged();
				}
			});

			return rootView;
		}
	}
}