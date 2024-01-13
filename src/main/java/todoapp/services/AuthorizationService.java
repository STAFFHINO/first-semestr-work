package todoapp.services;

import todoapp.forms.withoutDto.SignInForm;
import todoapp.forms.withoutDto.SignUpForm;
import todoapp.forms.withDto.UserDto;
import todoapp.exceptions.TodoException;

public interface AuthorizationService {
    UserDto signUp(SignUpForm form) throws TodoException;
    UserDto signIn(SignInForm form) throws TodoException;
}
