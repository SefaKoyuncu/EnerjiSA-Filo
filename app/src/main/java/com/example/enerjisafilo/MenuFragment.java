package com.example.enerjisafilo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class MenuFragment extends Fragment {

    private ConstraintLayout cLAracZimmetAlma,cLAracBilgisiGirisi,cLYakıtKartıTeslimi,
                             cLAracZimmetVerme, cLAracDegisimi, cLIkameAracTeslimi,cLayoutMenu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_menu, container, false);

        cLAracZimmetAlma=view.findViewById(R.id.cLAracZimmetAlma);
        cLAracBilgisiGirisi=view.findViewById(R.id.cLAracBilgisiGirisi);
        cLYakıtKartıTeslimi=view.findViewById(R.id.cLYakıtKartıTeslimi);

        cLAracZimmetVerme=view.findViewById(R.id.cLAracZimmetVerme);
        cLAracDegisimi=view.findViewById(R.id.cLAracDegisimi);
        cLIkameAracTeslimi=view.findViewById(R.id.cLIkameAracTeslimi);

        cLayoutMenu=view.findViewById(R.id.cLayoutMenu);
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.group_8113);
        Bitmap blurredBitmap = BlurBuilder.blur( getContext(), originalBitmap );
        cLayoutMenu.setBackground(new BitmapDrawable(getResources(), blurredBitmap));

        cLAracZimmetAlma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_araczimmetalmagecis);
            }
        });

        cLAracBilgisiGirisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_aracbilgigecis);
            }
        });

        cLYakıtKartıTeslimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_yakitkartigecis);
            }
        });

        cLAracZimmetVerme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Geliştirme aşamasındadır...",Toast.LENGTH_SHORT).show();

            }
        });
        cLAracDegisimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Geliştirme aşamasındadır...",Toast.LENGTH_SHORT).show();

            }
        });
        cLIkameAracTeslimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Geliştirme aşamasındadır...",Toast.LENGTH_SHORT).show();

            }
        });


        return view;
    }
}