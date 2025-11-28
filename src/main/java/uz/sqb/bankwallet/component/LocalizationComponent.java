
package uz.sqb.bankwallet.component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@AllArgsConstructor
@Slf4j
public class LocalizationComponent {

    private static ResourceBundleMessageSource messageSource;


    @Autowired
    LocalizationComponent(ResourceBundleMessageSource messageSource) {
        LocalizationComponent.messageSource = messageSource;
    }

    public String getMessage(String msgCode, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        try {
            return messageSource.getMessage(msgCode, args, locale);
        } catch (Exception e) {
            log.error(e.getMessage());
            return  msgCode;
        }
    }

}