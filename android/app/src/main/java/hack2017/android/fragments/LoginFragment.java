package hack2017.android.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import hack2017.android.interfaces.LoginVerifierListener;
import hack2017.android.texter.R;
import hack2017.android.utils.LoginVerifier;

public class LoginFragment extends Fragment implements LoginVerifierListener
{
    public LoginFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        final TextInputEditText
                email = (TextInputEditText) ((TextInputLayout)
                view.findViewById(R.id.email)).getEditText(),
                password = (TextInputEditText) ((TextInputLayout)
                        view.findViewById(R.id.password)).getEditText();
        final TextInputLayout organization = (TextInputLayout)
                view.findViewById(R.id.organization);
        email.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if(!hasFocus)
                {
                    String inputText = email.getText().toString();
                    if(!inputText.contains("@"))
                        organization.setVisibility(View.VISIBLE);
                    else
                        organization.setVisibility(View.GONE);
                }
            }
        });

        view.findViewById(R.id.login).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String emailInput = email.getText().toString(),
                       passwordInput = password.getText().toString(),
                       organizationInput = organization.getEditText().getText().toString();
                new LoginVerifier(getContext(), LoginFragment.this)
                        .verify(emailInput, passwordInput, organizationInput);

                InputMethodManager inputManager = (InputMethodManager)
                        getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(
                        getActivity().getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        return view;
    }

    @Override
    public void checkRequestStatus(boolean isSuccess)
    {

    }
}
