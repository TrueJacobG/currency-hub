import { MaterialCommunityIcons } from "@expo/vector-icons";
import { Dispatch, useState } from "react";
import { StyleSheet, Text, TextInput, View } from "react-native";
import { User } from "../../type/User";
import { isAuthCodeValidate, isEmailValidate, isFirstnameValidate, isNameValidate, isSurnameValidate } from "../../utils/validationUtils";

type Props = {
  user: User;
  setUser: Dispatch<User>;
};

const SigninComponent = ({ user, setUser }: Props) => {
  const [password, setPassword] = useState("");
  const [repassword, setRepassword] = useState("");

  const [showPassword, setShowPassword] = useState(false);
  const [showRePassword, setShowRePassword] = useState(false);

  const [showNameError, setShowNameError] = useState<boolean>(false);
  const [showFirstNameError, setShowFirstNameError] = useState<boolean>(false);
  const [showSurNameError, setShowSurNameError] = useState<boolean>(false);
  const [showEmailError, setShowEmailError] = useState<boolean>(false);
  const [showAuthCodeError, setShowAuthCodeError] = useState<boolean>(false);

  const toggleShowPassword = () => {
    setShowPassword(!showPassword);
  };
  const toggleShowRePassword = () => {
    setShowRePassword(!showRePassword);
  };

  const onChangeName = (name: string) => {
    setShowNameError(!isNameValidate(name));
    setUser({ ...user, name: name });
  };

  const onChangeFirstname = (firstname: string) => {
    setShowFirstNameError(!isFirstnameValidate(firstname));
    setUser({ ...user, firstname: firstname });
  };

  const onChangeSurname = (surname: string) => {
    setShowSurNameError(!isSurnameValidate(surname));
    setUser({ ...user, surname: surname });
  };

  const onChangeEmail = (email: string) => {
    setShowEmailError(!isEmailValidate(email));
    setUser({ ...user, email: email });
  };

  const onChangeAuthCode = (authCode: string) => {
    setShowAuthCodeError(!isAuthCodeValidate(authCode));
    setUser({ ...user, authCode: authCode });
  };

  const onChangePassword = (password: string) => {
    setPassword(password);
    onChangeAuthCode(password);
  };

  const onChangeRePassword = (repassword: string) => {
    setRepassword(repassword);
    onChangeAuthCode(repassword);
  };

  return (
    <View>
      <Text style={styles.text}>Welcome to CurrencyHub</Text>
      <Text style={styles.textintro}>Do you want to create an account?</Text>
      <TextInput style={styles.textinput} onChangeText={onChangeName} placeholder={"Enter your name"} />
      {showNameError && <Text style={styles.textError}>Name is not valid!</Text>}
      <TextInput style={styles.textinput} onChangeText={onChangeFirstname} placeholder={"Enter your firstname"} />
      {showFirstNameError && <Text style={styles.textError}>Firstname is not valid!</Text>}
      <TextInput style={styles.textinput} onChangeText={onChangeSurname} placeholder={"Enter your surname"} />
      {showSurNameError && <Text style={styles.textError}>Surname is not valid!</Text>}
      <TextInput style={styles.textinput} onChangeText={onChangeEmail} placeholder={"Enter your email"} />
      {showEmailError && <Text style={styles.textError}>Email is not valid!</Text>}
      <TextInput
        style={styles.textinput}
        secureTextEntry={!showPassword}
        onChangeText={onChangePassword}
        placeholder={"Enter your password"}
      />
      <MaterialCommunityIcons
        name={showPassword ? "eye" : "eye-off"}
        size={24}
        color="#aaa"
        style={styles.icon}
        onPress={toggleShowPassword}
      />
      {showAuthCodeError && <Text style={styles.textError}>Password is not valid!</Text>}
      <TextInput
        style={styles.textinput}
        secureTextEntry={!showRePassword}
        onChangeText={onChangeRePassword}
        placeholder={"Repeat your password"}
      />
      <MaterialCommunityIcons
        name={showRePassword ? "eye" : "eye-off"}
        size={24}
        color="#aaa"
        style={styles.icon}
        onPress={toggleShowRePassword}
      />
      {showAuthCodeError && <Text style={styles.textError}>Password is not valid!</Text>}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    textAlign: "center",
    textAlignVertical: "center",
    fontSize: 18,
    width: 200,
    backgroundColor: "yellow",
  },
  textinput: {
    marginTop: 10,
    borderRadius: 30,
    padding: 10,
    borderWidth: 1,
    fontSize: 20,
  },
  text: {
    fontSize: 30,
    textAlign: "center",
    marginTop: 30,
  },
  icon: {
    marginLeft: 10,
  },
  textintro: {
    fontSize: 15,
    textAlign: "center",
    paddingBottom: 10,
    paddingTop: 10,
  },
  textError: {
    fontSize: 10,
    color: "#FF0000",
    textAlign: "left",
    marginLeft: 10
  }
});

export default SigninComponent;
