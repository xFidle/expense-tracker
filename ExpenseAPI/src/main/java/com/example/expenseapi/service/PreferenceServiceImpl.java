package com.example.expenseapi.service;

import com.example.expenseapi.dto.PreferenceUpdateDTO;
import com.example.expenseapi.pojo.Currency;
import com.example.expenseapi.pojo.MethodOfPayment;
import com.example.expenseapi.pojo.Preference;
import com.example.expenseapi.repository.CurrencyRepository;
import com.example.expenseapi.repository.MethodOfPaymentRepository;
import com.example.expenseapi.repository.PreferenceRepository;
import com.example.expenseapi.utils.AuthHelper;
import com.example.expenseapi.utils.CurrencyRatesFetcher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PreferenceServiceImpl extends GenericServiceImpl<Preference, Long> implements PreferenceService {
    private final PreferenceRepository preferenceRepository;
    private final CurrencyRepository currencyRepository;
    private final MethodOfPaymentRepository methodOfPaymentRepository;

    public PreferenceServiceImpl(PreferenceRepository repository, PreferenceRepository preferenceRepository, CurrencyRepository currencyRepository, MethodOfPaymentRepository methodOfPaymentRepository) {
        super(repository);
        this.preferenceRepository = preferenceRepository;
        this.currencyRepository = currencyRepository;
        this.methodOfPaymentRepository = methodOfPaymentRepository;
    }

    @Override
    public Preference getUserPreferences() {
        return preferenceRepository.getPreferenceById(AuthHelper.getUser().getId());
    }

    @Override
    public Preference updateUserPreferences(PreferenceUpdateDTO preferenceUpdateDTO) {
        Preference pref = getUserPreferences();
        Optional<Currency> currency = currencyRepository.findBySymbol(preferenceUpdateDTO.getCurrencySymbol());
        if (currency.isEmpty() && preferenceUpdateDTO.getCurrencySymbol() != null) {
            String symbol = preferenceUpdateDTO.getCurrencySymbol();
            Currency newCurrency = new Currency(symbol, CurrencyRatesFetcher.getCurrencyRates(symbol));
            currencyRepository.save(newCurrency);
            pref.setCurrency(newCurrency);
        } else currency.ifPresent(pref::setCurrency);
        Optional<MethodOfPayment> method = methodOfPaymentRepository.findByName(preferenceUpdateDTO.getMethodOfPayment());
        if (method.isEmpty() && preferenceUpdateDTO.getMethodOfPayment() != null) {
            MethodOfPayment newMethod = new MethodOfPayment(preferenceUpdateDTO.getMethodOfPayment());
            methodOfPaymentRepository.save(newMethod);
            pref.setMethod(newMethod);
        }
        else method.ifPresent(pref::setMethod);
        if (preferenceUpdateDTO.getLanguage() != null)
            pref.setLanguage(preferenceUpdateDTO.getLanguage());
        return preferenceRepository.save(pref);
    }
}
