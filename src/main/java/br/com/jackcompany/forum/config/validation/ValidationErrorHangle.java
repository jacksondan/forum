package br.com.jackcompany.forum.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.jackcompany.forum.config.validation.dto.JsonErrorDTO;

@RestControllerAdvice
public class ValidationErrorHangle {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<JsonErrorDTO> handle(MethodArgumentNotValidException exception) {
		
		List<JsonErrorDTO> errorList = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		
		fieldErrors.forEach(e -> {
			String field = e.getField();
			String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			JsonErrorDTO error = new JsonErrorDTO(field, message);
			errorList.add(error);
		});
		
		return errorList;
	}

}
