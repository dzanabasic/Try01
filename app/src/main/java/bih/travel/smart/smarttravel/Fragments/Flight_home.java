package bih.travel.smart.smarttravel.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import bih.travel.smart.smarttravel.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Flight_home.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Flight_home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Flight_home extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    /*my variables*/
    Context context;
    private DatePickerDialog.OnDateSetListener mDateSetListener_dep_date;
    private DatePickerDialog.OnDateSetListener mDateSetListener_dest_date;

    private static final String TAG = "Hotels";
    private Button buttonDepartureDate;
    private Button buttonDestinationDate;
    private EditText departure;
    private EditText destination;
    String mDeparture="";
    String mDestionation="";
    private Button search;
    /*end of my variables*/


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Flight_home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Flight_home.
     */
    // TODO: Rename and change types and number of parameters
    public static Flight_home newInstance(String param1, String param2) {
        Flight_home fragment = new Flight_home();
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
         View rootView = inflater.inflate(R.layout.fragment_flight_home, container, false);
        context = rootView.getContext(); // Assign your rootView to context

        Button buttonHotels = (Button) rootView.findViewById(R.id.hotel_button);
        buttonHotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                Hotels hotelsFragment=new Hotels();
                fragmentTransaction.replace(R.id.fragment_container,hotelsFragment);
                fragmentTransaction.commit();

                //Pass the context and the Activity class you need to open from the Fragment Class, to the Intent
               /* Intent intent = new Intent(context, Hotels.class);
                start(intent);*/
            }
        });

        /*DATE PICKER CODE*/
        buttonDepartureDate=(Button) rootView.findViewById(R.id.departure_date);
        buttonDestinationDate=(Button) rootView.findViewById(R.id.return_date);

        buttonDepartureDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener_dep_date,
                        year,month,day);


                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener_dep_date= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                buttonDepartureDate.setText(date);
            }
        };


        /*datepicker for checkout*/

        buttonDestinationDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener_dest_date,
                        year,month,day);


                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener_dest_date= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                buttonDestinationDate.setText(date);
            }
        };

        /*RADIO BUTTON ON CHANGE*/

        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.myradiogroup);
        final RadioButton oneWay=(RadioButton) rootView.findViewById(R.id.one_way_id);
        final RadioButton twoWay=(RadioButton) rootView.findViewById(R.id.return_id);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(oneWay.isChecked()){
                    buttonDestinationDate.setVisibility(View.GONE);
                    buttonDepartureDate.getLayoutParams().width= ViewGroup.LayoutParams.MATCH_PARENT;//set width to max

                }
                if(twoWay.isChecked()){
                    buttonDestinationDate.setVisibility(View.VISIBLE);
                    buttonDepartureDate.getLayoutParams().width= ViewGroup.LayoutParams.MATCH_PARENT/2;//set width to max


                }
            }
        });
        /*end of radio button change*/



        /*get value of destionation editText*/
        destination=(EditText) rootView.findViewById(R.id.destination_text);
        mDestionation=destination.getText().toString();


        /*end of get value of destination editText*/
        search=(Button) rootView.findViewById(R.id.search_flights);
        departure=(EditText) rootView.findViewById(R.id.departure_text);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*get value of departure editText*/
                mDeparture=departure.getText().toString();
                //destination.setText(mDeparture);

                /*end of get value of departure editText*/
                }

            });

        return rootView;
    }


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
