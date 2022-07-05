package com.example.enerjisafilo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AracZimmetAlmaFragment extends Fragment {

    private ImageView imageViewZimmetKapat;
    private Button buttonZimmetTamamla,buttonZimmetTemizle;
    private EditText editTextZimmetSirketKodu, editTextZimmetSicil, editTextZimmetKullanımAmacı,
            editTextZimmetAracPlakası, editTextZimmetAracAlmaTarihi, editTextZimmetEhliyet;
    private ConstraintLayout cLayoutAracZimmet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_arac_zimmet_alma, container, false);

        imageViewZimmetKapat=view.findViewById(R.id.imageViewZimmetKapat);
        buttonZimmetTamamla=view.findViewById(R.id.buttonZimmetTamamla);
        buttonZimmetTemizle=view.findViewById(R.id.buttonZimmetTemizle);

        editTextZimmetSirketKodu=view.findViewById(R.id.editTextZimmetSirketKodu);
        editTextZimmetSicil=view.findViewById(R.id.editTextZimmetSicil);
        editTextZimmetKullanımAmacı=view.findViewById(R.id.editTextZimmetKullanımAmacı);
        editTextZimmetAracPlakası=view.findViewById(R.id.editTextZimmetAracPlakası);
        editTextZimmetAracAlmaTarihi=view.findViewById(R.id.editTextZimmetAracAlmaTarihi);
        editTextZimmetEhliyet=view.findViewById(R.id.editTextZimmetEhliyet);

        // date formatter
        TextWatcher tw = new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]|\\.", "");
                    String cleanC = current.replaceAll("[^\\d.]|\\.", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        mon = mon < 1 ? 1 : mon > 12 ? 12 : mon;
                        cal.set(Calendar.MONTH, mon-1);
                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;

                    editTextZimmetAracAlmaTarihi.setText(current);
                    editTextZimmetAracAlmaTarihi.setSelection(sel < current.length() ? sel : current.length());
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        };

       editTextZimmetAracAlmaTarihi.addTextChangedListener(tw);

        cLayoutAracZimmet=view.findViewById(R.id.cLayoutAracZimmet);
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.group_8113);
        Bitmap blurredBitmap = BlurBuilder.blur( getContext(), originalBitmap );
        cLayoutAracZimmet.setBackground(new BitmapDrawable(getResources(), blurredBitmap));

        imageViewZimmetKapat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_zimmet_to_menu);

            }
        });

        buttonZimmetTamamla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(edittextBosMu(editTextZimmetSicil) || edittextBosMu(editTextZimmetAracPlakası) || edittextBosMu(editTextZimmetAracAlmaTarihi) || edittextBosMu(editTextZimmetEhliyet))
                {
                    Toast.makeText(getContext(),"(*) İşaretli alanları doldurunuz!",Toast.LENGTH_LONG).show();
                }
                else if(!edittextBosMu(editTextZimmetSicil) && !edittextBosMu(editTextZimmetAracPlakası) && !edittextBosMu(editTextZimmetAracAlmaTarihi) && !edittextBosMu(editTextZimmetEhliyet))
                {
                    Toast.makeText(getContext(),"Başarıyla kaydedildi",Toast.LENGTH_LONG).show();
                }

            }
        });

        buttonZimmetTemizle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                editTextZimmetSirketKodu.getText().clear();
                editTextZimmetSicil.getText().clear();
                editTextZimmetKullanımAmacı.getText().clear();
                editTextZimmetAracPlakası.getText().clear();
                editTextZimmetAracAlmaTarihi.getText().clear();
                editTextZimmetEhliyet.getText().clear();

            }
        });

        return view;
    }

    public boolean edittextBosMu(EditText editText)
    {
       return editText.getText().toString().equals("");
    }

}