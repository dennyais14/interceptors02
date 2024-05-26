package co.develhope.Interceptor02.interceptors;

import co.develhope.Interceptor02.entities.Month;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class MonthInterceptor implements HandlerInterceptor {
    private final List<Month> months = Arrays.asList(
            new Month(1, "January", "Gennaio", "Januar"),
            new Month(2, "February", "Febbraio", "Februar"),
            new Month(3, "March", "Marzo", "März"),
            new Month(4, "April", "Aprile", "April"),
            new Month(5, "May", "Maggio", "Mai"),
            new Month(6, "June", "Giugno", "Juni")
    );

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String monthNumber = request.getHeader("monthNumber");

        if (monthNumber == null || monthNumber.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }
        Optional<Month> foundMonth = months.stream()
                .filter(month -> month.getMonthNumber() == Integer.parseInt(monthNumber))
                .findFirst();

        if (foundMonth.isPresent()) {
            request.setAttribute("month", foundMonth.get());
        } else {
            Month emptyMonth = new Month();
            emptyMonth.setEnglishName("nope");
            emptyMonth.setItalianName("nope");
            emptyMonth.setGermanName("nope");
            request.setAttribute("month", emptyMonth);
        }

        return true;

    }
}
