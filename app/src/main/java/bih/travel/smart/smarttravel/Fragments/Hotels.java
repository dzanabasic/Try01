package bih.travel.smart.smarttravel.Fragments;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;

import bih.travel.smart.smarttravel.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Hotels.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Hotels#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Hotels extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /*my variables*/
    Context context;
    private DatePickerDialog.OnDateSetListener mDateSetListener_in;
    private DatePickerDialog.OnDateSetListener mDateSetListener_out;

    private static final String TAG = "Hotels";
    private Button buttoncheckIn;
    private Button buttoncheckOut;


    private OnFragmentInteractionListener mListener;

    public Hotels() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Hotels.
     */
    // TODO: Rename and change types and number of parameters
    public static Hotels newInstance(String param1, String param2) {
        Hotels fragment = new Hotels();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_hotels, container, false);
        context = rootView.getContext(); // Assign your rootView to context

        Button buttonFlights = (Button) rootView.findViewById(R.id.flight_button_hotels);
        buttonFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                Flight_home flightsFragment=new Flight_home();
                fragmentTransaction.replace(R.id.fragment_container,flightsFragment);
                fragmentTransaction.commit();

                //Pass the context and the Activity class you need to open from the Fragment Class, to the Intent
               /* Intent intent = new Intent(context, Hotels.class);
                start(intent);*/
            }
        });

        /*disable buttons after checkbox*/
        buttoncheckIn= (Button) rootView.findViewById(R.id.check_in);
        buttoncheckOut= (Button) rootView.findViewById(R.id.check_out);
        CheckBox checkBox= (CheckBox) rootView.findViewById(R.id.checkbox_dates);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked ) {
                    buttoncheckIn.setEnabled(false);
                    buttoncheckOut.setEnabled(false);
                    /*THIS ONE WORKS-IT IS CHOSEN*/
                    buttoncheckIn.setVisibility(View.GONE);
                    buttoncheckOut.setVisibility(View.GONE);
                }

                else{
                    buttoncheckIn.setEnabled(true);
                    buttoncheckOut.setEnabled(true);
                    /*THIS ONE IS CHOSEN TO WORK*/
                    buttoncheckIn.setVisibility(View.VISIBLE);
                    buttoncheckOut.setVisibility(View.VISIBLE);
                }

            }
        });
        /*opening datepicker*/
        buttoncheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener_in,
                        year,month,day);


                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener_in= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                buttoncheckIn.setText(date);
            }
        };


        /*datepicker for checkout*/

        buttoncheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener_out,
                        year,month,day);


                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener_out= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                buttoncheckOut.setText(date);
            }
        };


        return rootView;
    }// Inflate the layout for this fragment


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
