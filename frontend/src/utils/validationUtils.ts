export const isEmailValidate = (email: string) => {
  return email.includes(".") && email.includes("@") && email.length >= 5;
};

export const isAuthCodeValidate = (authCode: string) => {
  return authCode.length >= 8 && authCode.length <= 20;
};

const isNameSomethingValidate = (name: string) => {
  return name.length >= 3 && name.length <= 50;
};

export const isNameValidate = (name: string) => {
  return isNameSomethingValidate(name);
};

export const isFirstnameValidate = (name: string) => {
  return isNameSomethingValidate(name);
};

export const isSurnameValidate = (name: string) => {
  return isNameSomethingValidate(name);
};
