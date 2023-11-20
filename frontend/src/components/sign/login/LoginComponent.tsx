import { useState } from "react";
import { StyleSheet, Text, TextInput, View } from "react-native";
import { LoginUser } from "../../../type/LoginUser";

type Props = {
  loginUser: LoginUser;
  setLoginUser: any;
};

const LoginComponent = ({ loginUser, setLoginUser }: Props) => {
  // TODO:
  // add show error
  // add validation
  // and
  // secure password

  const [errorMessage, isErrorMessage] = useState<string>("");

  const onChangeEmail = (email: string) => {
    let emailIsValid = validateEmail(email);

    if (!emailIsValid) {
      isErrorMessage("Zle haslo!");
    }

    setLoginUser({ ...loginUser, email: email });
    isErrorMessage("");
  };

  const validateEmail = (email: string) => {
    if (email.length < 8) {
      return false;
    }

    // ...

    return true;
  };

  const onChangeAuthCode = (authCode: string) => {
    setLoginUser({ ...loginUser, authCode: authCode });
  };

  return (
    <View style={styles.container}>
      <Text style={styles.text}>Login to your account:</Text>
      <TextInput style={styles.textinput} onChangeText={onChangeEmail} placeholder={"Enter your email"} />
      {errorMessage}
      <TextInput style={styles.textinput} onChangeText={onChangeAuthCode} placeholder={"Enter your password"} />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    textAlign: "center",
    textAlignVertical: "center",
    fontSize: 40,
    width: 300,
    height: 200,
    backgroundColor: "yellow",
    marginLeft: 50,
    marginRight: 100,
    marginTop: 100,
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
});

export default LoginComponent;
