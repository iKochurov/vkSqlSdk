package com.htccs.android.vkmusic.finder;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.htccs.android.vkmusic.FragmentInteraction;
import com.htccs.android.vkmusic.PostsActivity;
import com.htccs.android.vkmusic.R;

public class FinderFragment extends Fragment {

    public static final String TAG = FinderFragment.class.getSimpleName();
    private FragmentInteraction fragmentInteraction;

    public static FinderFragment newInstance() {
        Bundle args = new Bundle();
        FinderFragment fragment = new FinderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PostsActivity) {
            fragmentInteraction = (PostsActivity) context;
        } else {
            throw new IllegalStateException("your activity must implement " + FragmentInteraction.class.getCanonicalName());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_finder, container, false);
        final EditText editText = view.findViewById(R.id.edit_finder);
        Button button = view.findViewById(R.id.find_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentInteraction.onFindingGroup(editText.getText().toString());
            }
        });
        return view;
    }
}
