package com.example.enerjisafilo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.Slider;

import java.util.ArrayList;
import java.util.List;

public class AracBilgileriFragment extends Fragment {

    private ImageView imageViewAracBilgileriKapat;
    private Button buttonAracBilgileriKaydet, buttonAracBilgileriTemizle;
    private EditText editTextAracBilgileriPlaka, editTextAracBilgileriAracKM;
    private SwitchCompat switchCompat1,switchCompat2,switchCompat3,switchCompat4,switchCompat5,switchCompat6,
                    switchCompat7, switchCompat8, switchCompat9, switchCompat10, switchCompat11, switchCompat12,
                    switchCompat13, switchCompat14, switchCompat15, switchCompat16, switchCompat17, switchCompat18;
    private ArrayList<SwitchCompat> switchCompatArrayList=new ArrayList<>();
    private Slider slider;
    private ConstraintLayout cLayoutAracBilgileri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_arac_bilgileri, container, false);

        imageViewAracBilgileriKapat=view.findViewById(R.id.imageViewAracBilgileriKapat);
        buttonAracBilgileriKaydet=view.findViewById(R.id.buttonAracBilgileriKaydet);
        buttonAracBilgileriTemizle=view.findViewById(R.id.buttonAracBilgileriTemizle);

        editTextAracBilgileriPlaka =view.findViewById(R.id.editTextAracBilgileriPlaka);
        editTextAracBilgileriAracKM=view.findViewById(R.id.editTextAracBilgileriAracKM);

        slider=view.findViewById(R.id.slider);

        switchCompat1=view.findViewById(R.id.switchCompat1);    switchCompatArrayList.add(switchCompat1);
        switchCompat2=view.findViewById(R.id.switchCompat2);    switchCompatArrayList.add(switchCompat2);
        switchCompat3=view.findViewById(R.id.switchCompat3);    switchCompatArrayList.add(switchCompat3);
        switchCompat4=view.findViewById(R.id.switchCompat4);    switchCompatArrayList.add(switchCompat4);
        switchCompat5=view.findViewById(R.id.switchCompat5);    switchCompatArrayList.add(switchCompat5);
        switchCompat6=view.findViewById(R.id.switchCompat6);    switchCompatArrayList.add(switchCompat6);
        switchCompat7=view.findViewById(R.id.switchCompat7);    switchCompatArrayList.add(switchCompat7);
        switchCompat8=view.findViewById(R.id.switchCompat8);    switchCompatArrayList.add(switchCompat8);
        switchCompat9=view.findViewById(R.id.switchCompat9);    switchCompatArrayList.add(switchCompat9);
        switchCompat10=view.findViewById(R.id.switchCompat10);  switchCompatArrayList.add(switchCompat10);
        switchCompat11=view.findViewById(R.id.switchCompat11);  switchCompatArrayList.add(switchCompat11);
        switchCompat12=view.findViewById(R.id.switchCompat12);  switchCompatArrayList.add(switchCompat12);
        switchCompat13=view.findViewById(R.id.switchCompat13);  switchCompatArrayList.add(switchCompat13);
        switchCompat14=view.findViewById(R.id.switchCompat14);  switchCompatArrayList.add(switchCompat14);
        switchCompat15=view.findViewById(R.id.switchCompat15);  switchCompatArrayList.add(switchCompat15);
        switchCompat16=view.findViewById(R.id.switchCompat16);  switchCompatArrayList.add(switchCompat16);
        switchCompat17=view.findViewById(R.id.switchCompat17);  switchCompatArrayList.add(switchCompat17);
        switchCompat18=view.findViewById(R.id.switchCompat18);  switchCompatArrayList.add(switchCompat18);

        slider.setLabelFormatter(new LabelFormatter() {
            @NonNull
            @Override
            public String getFormattedValue(float value) {
                System.out.println("value: " + value);

                if (value == 0.0f){
                    return "E";
                }
                else if (value == 0.125f){
                    return "1/8";
                }
                else if (value == 0.25f){
                    return "1/4";
                }
                else if (value == 0.375f){
                    return "3/8";
                }
                else if (value == 0.5f){
                    return "1/2";
                }
                else if (value == 0.625f){
                    return "5/8";
                }
                else if (value == 0.75f){
                    return "3/4";
                }
                else if (value == 0.875f){
                    return "7/8";
                }
                else{
                    return "F";
                }
            }
        });

        cLayoutAracBilgileri=view.findViewById(R.id.cLayoutAracBilgileri);
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.group_8113);
        Bitmap blurredBitmap = BlurBuilder.blur( getContext(), originalBitmap );
        cLayoutAracBilgileri.setBackground(new BitmapDrawable(getResources(), blurredBitmap));


        imageViewAracBilgileriKapat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_aracbilgileri_to_menu);
            }
        });

        buttonAracBilgileriKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(editTextAracBilgileriPlaka.getText().toString().equals("") || editTextAracBilgileriAracKM.getText().toString().equals("") )
                {
                    Toast.makeText(getContext(),"(*) İşaretli alanları doldurunuz!",Toast.LENGTH_LONG).show();
                }
                else if (!(editTextAracBilgileriPlaka.getText().toString().equals("")) && !(editTextAracBilgileriAracKM.getText().toString().equals("")))
                {
                    Toast.makeText(getContext(),"Başarıyla kaydedildi",Toast.LENGTH_LONG).show();
                }

            }
        });

        buttonAracBilgileriTemizle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextAracBilgileriPlaka.getText().clear();
                editTextAracBilgileriAracKM.getText().clear();
                switchReset(switchCompatArrayList);

            }
        });

        return view;
    }

    public void switchReset(List<SwitchCompat> list)
    {
        for (SwitchCompat s:list)
        {
            s.setChecked(false);
        }
    }

}