package com.example.enerjisafilo;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.InetAddress;
import java.util.regex.Pattern;


public class LoginFragment extends Fragment {

    private Button buttonGirisYap;
    private EditText editTextMail,editTextSifre;
    private String mail;
    private String sifre;
    private ImageView imageViewSifreyiGosterGizle;
    private Dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_login, container, false);

        buttonGirisYap=view.findViewById(R.id.buttonGirisYap);
        editTextMail=view.findViewById(R.id.editTextZimmetSirketKodu);
        editTextSifre=view.findViewById(R.id.editTextZimmetKullanımAmacı);
        imageViewSifreyiGosterGizle=view.findViewById(R.id.imageViewSifreyiGosterGizle);

        dialog=new Dialog(getContext());

        buttonGirisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mail=editTextMail.getText().toString().replaceAll("\\s","");
                sifre=editTextSifre.getText().toString().replaceAll("\\s","");

                if(!isNetworkAvailable(getContext()))
                {
                    dialog.setContentView(R.layout.no_internet_connection);
                    Button buttonTamamNoConnection=dialog.findViewById(R.id.buttonTamamNoConnection);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    buttonTamamNoConnection.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }

                else
                {
                    if(!isValidPassword(sifre)&&!isValidMail(mail))
                    {
                        Toast.makeText(getContext(),"Geçerli mail adresi ve şifre giriniz!",Toast.LENGTH_SHORT).show();
                    }
                    else if(!isValidMail(mail))
                    {
                        Toast.makeText(getContext(),"Geçerli mail adresi giriniz!",Toast.LENGTH_SHORT).show();
                        //Snackbar.make(view,"Geçerli mail adresi giriniz!",Snackbar.LENGTH_SHORT).show();
                    }

                    else if(isValidMail(mail)&&isValidPassword(sifre))
                    {
                        ProgressDialog progressDialog = ProgressDialog.show(getContext(), "",
                                "Giriş Yapılıyor. Lütfen Bekleyiniz...", true);

                        progressDialog.show();

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                progressDialog.dismiss();
                                Navigation.findNavController(view).navigate(R.id.action_menuyegecis);
                            }
                        }, 3000);
                    }
                    else
                    {
                        Toast.makeText(getContext(),"Geçerli şifre giriniz!\n\n(En az 1 büyük harf, en az 1 küçük harf, en az 1 sayı,\nen az 1 özel karakter(.#?!@$%^&*-)) ve en az 8 karakterden oluşmalıdır.)",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        imageViewSifreyiGosterGizle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editTextSifre.getInputType() == 144 ){    // 144 mean is that if we can see the password now
                    editTextSifre.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);  // close the password
                    imageViewSifreyiGosterGizle.setImageResource(R.drawable.visibility_off);
                    editTextSifre.setHint("*************");
                }
                else {
                    editTextSifre.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);      // show the password
                    imageViewSifreyiGosterGizle.setImageResource(R.drawable.visibility);
                    editTextSifre.setHint("Sifre giriniz");
                }
                editTextSifre.setSelection(editTextSifre.length());   // set cursor position end of the password
            }
        });

        return view;

    }

    public static boolean isValidMail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static boolean isValidPassword(String password)
    {
        String passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[.#?!@$%^&*-]).{8,}$";

        Pattern pat = Pattern.compile(passwordRegex);
        if (password == null)
            return false;
        return pat.matcher(password).matches();
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public boolean internetIsConnected() {
        try {
            String command = "ping -c 1 google.com";
            return (Runtime.getRuntime().exec(command).waitFor() == 0);
        } catch (Exception e) {
            return false;
        }
    }
}