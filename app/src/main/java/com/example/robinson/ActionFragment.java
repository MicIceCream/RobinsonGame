package com.example.robinson;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ActionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActionFragment.
     */


    // https://developer.alexanderklimov.ru/android/fragment2.php
    // Здесь можно поискать инфу о фрагментах, не забудь!




    // TODO: Rename and change types and number of parameters
    public static ActionFragment newInstance(String param1, String param2) {
        ActionFragment fragment = new ActionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        try {
            postMan = (PostMan) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement onSomeEventListener");
        }
    }
    PostMan postMan;

//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            postMan = (PostMan) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_action, container, false);
        TextView tvAct1 = (TextView) v.findViewById(R.id.tvAct1);
        TextView tvAct2 = (TextView) v.findViewById(R.id.tvAct2);
        TextView tvAct3 = (TextView) v.findViewById(R.id.tvAct3);
        TextView tvCost1 = (TextView) v.findViewById(R.id.tvCost1);
        TextView tvCost2 = (TextView) v.findViewById(R.id.tvCost2);
        TextView tvCost3 = (TextView) v.findViewById(R.id.tvCost3);

        ImageButton btnAct1 = (ImageButton) v.findViewById(R.id.btnAct1);
        ImageButton btnAct2 = (ImageButton) v.findViewById(R.id.btnAct2);
        ImageButton btnAct3 = (ImageButton) v.findViewById(R.id.btnAct3);

        tvAct1.setText(getArguments().getString("act1"));
        tvAct2.setText(getArguments().getString("act2"));
        tvAct3.setText(getArguments().getString("act3"));
        tvCost1.setText(getArguments().getString("cost1"));
        tvCost2.setText(getArguments().getString("cost2"));
        tvCost3.setText(getArguments().getString("cost3"));

        btnAct1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Работает?", Toast.LENGTH_SHORT).show();
//                ((TextView)getActivity().findViewById(R.id.tvMaterials)).setText("Access from Fragment1");
                postMan.onButtonSelected(1);
            }
        });
        btnAct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Кажется, работает.", Toast.LENGTH_SHORT).show();
                postMan.onButtonSelected(2);
            }
        });
        btnAct3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Точно работает!", Toast.LENGTH_SHORT).show();
                postMan.onButtonSelected(3);
            }
        });


        return v;
    }
}