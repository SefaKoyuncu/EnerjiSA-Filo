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
import android.widget.Toast;

import java.util.Calendar;

public class YakitKartiFragment extends Fragment {

    private ImageView imageViewYakitKartiKapat;
    private Button buttonYakitKartiTamamla, buttonYakitKartiTemizle;
    private EditText editTextYakitKartiYakitKartNo, editTextYakitKartiSicilNo, editTextYakitKartiVerilmeNedeni, editTextYakitKartiKullanimAmaci,
                     editTextYakitKartiSirketKodu, editTextYakitKartiOpMerkezBilgisi, editTextYakitKartiPlaka, editTextYakitKartiKartVerilmeTarihi;
    private ConstraintLayout cLayoutYakitKarti;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_yakit_karti, container, false);

        imageViewYakitKartiKapat=view.findViewById(R.id.imageViewYakitKartiKapat);
        buttonYakitKartiTamamla=view.findViewById(R.id.buttonYakitKartiTamamla);
        buttonYakitKartiTemizle=view.findViewById(R.id.buttonYakitKartiTemizle);

        editTextYakitKartiYakitKartNo=view.findViewById(R.id.editTextYakitKartiYakitKartNo);
        editTextYakitKartiSicilNo =view.findViewById(R.id.editTextYakitKartiSicilNo);
        editTextYakitKartiVerilmeNedeni=view.findViewById(R.id.editTextYakitKartiVerilmeNedeni);
        editTextYakitKartiKullanimAmaci=view.findViewById(R.id.editTextYakitKartiKullanimAmaci);
        editTextYakitKartiSirketKodu=view.findViewById(R.id.editTextYakitKartiSirketKodu);
        editTextYakitKartiOpMerkezBilgisi=view.findViewById(R.id.editTextYakitKartiOpMerkezBilgisi);
        editTextYakitKartiPlaka=view.findViewById(R.id.editTextYakitKartiPlaka);
        editTextYakitKartiKartVerilmeTarihi=view.findViewById(R.id.editTextYakitKartiKartVerilmeTarihi);

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
                    editTextYakitKartiKartVerilmeTarihi.setText(current);
                    editTextYakitKartiKartVerilmeTarihi.setSelection(sel < current.length() ? sel : current.length());
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        };

        editTextYakitKartiKartVerilmeTarihi.addTextChangedListener(tw);

        cLayoutYakitKarti=view.findViewById(R.id.cLayoutYakitKarti);
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.group_8113);
        Bitmap blurredBitmap = BlurBuilder.blur( getContext(), originalBitmap );
        cLayoutYakitKarti.setBackground(new BitmapDrawable(getResources(), blurredBitmap));

        buttonYakitKartiTemizle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editTextYakitKartiYakitKartNo.getText().clear();
                editTextYakitKartiSicilNo.getText().clear();
                editTextYakitKartiVerilmeNedeni.getText().clear();
                editTextYakitKartiKullanimAmaci.getText().clear();
                editTextYakitKartiSirketKodu.getText().clear();
                editTextYakitKartiOpMerkezBilgisi.getText().clear();
                editTextYakitKartiPlaka.getText().clear();
                editTextYakitKartiKartVerilmeTarihi.getText().clear();
            }
        });


        imageViewYakitKartiKapat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_yakitkarti_to_menu);
            }
        });

        buttonYakitKartiTamamla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(edittextBosMu(editTextYakitKartiYakitKartNo) || edittextBosMu(editTextYakitKartiSicilNo) || edittextBosMu(editTextYakitKartiPlaka) || edittextBosMu(editTextYakitKartiKartVerilmeTarihi))
                {
                    Toast.makeText(getContext(),"(*) İşaretli alanları doldurunuz!",Toast.LENGTH_LONG).show();

                }
                else if(!edittextBosMu(editTextYakitKartiYakitKartNo) && !edittextBosMu(editTextYakitKartiSicilNo) && !edittextBosMu(editTextYakitKartiPlaka) && !edittextBosMu(editTextYakitKartiKartVerilmeTarihi))
                {
                    Toast.makeText(getContext(),"Başarıyla kaydedildi",Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;

    }

    public boolean edittextBosMu(EditText editText)
    {
        return editText.getText().toString().equals("");
    }
}