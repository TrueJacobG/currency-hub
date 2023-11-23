import { MaterialCommunityIcons } from "@expo/vector-icons";
import { Dispatch, useState } from "react";
import { StyleSheet, Text, TextInput, View } from "react-native";
import { LoginUser } from "../../type/LoginUser";
import { isAuthCodeValidate, isEmailValidate } from "../../utils/validationUtils";

type Props = {
  loginUser: LoginUser;
  setLoginUser: Dispatch<LoginUser>;
};

const LoginComponent = ({ loginUser, setLoginUser }: Props) => {
  const [showPassword, setShowPassword] = useState(false);

  const [showEmailError, setShowEmailError] = useState<boolean>(false);
  const [showAuthCodeError, setShowAuthCodeError] = useState<boolean>(false);

  const toggleShowPassword = () => {
    setShowPassword(!showPassword);
  };

  const onChangeEmail = (email: string) => {
    setShowEmailError(!isEmailValidate(email));
    setLoginUser({ ...loginUser, email: email });
  };

  const onChangeAuthCode = (authCode: string) => {
    setShowAuthCodeError(!isAuthCodeValidate(authCode));
    setLoginUser({ ...loginUser, authCode: authCode });
  };

  return (
    <View style={styles.container}>
      <Text style={styles.text}>Login to your account:</Text>
      <TextInput style={styles.textinput} onChangeText={onChangeEmail} placeholder={"Enter your email"} />
      {showEmailError && <Text>Email is not valid!</Text>}
      <TextInput
        style={styles.textinput}
        secureTextEntry={!showPassword}
        onChangeText={onChangeAuthCode}
        placeholder={"Enter your password"}
      />
      {showAuthCodeError && <Text>Password is too short / too long!</Text>}
      <MaterialCommunityIcons
        name={showPassword ? "eye" : "eye-off"}
        size={24}
        color="#aaa"
        style={styles.icon}
        onPress={toggleShowPassword}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    textAlign: "center",
    textAlignVertical: "center",
    fontSize: 40,
    width: 300,
    height: 250,
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
  icon: {
    marginLeft: 10,
  },
  text: {
    fontSize: 30,
    textAlign: "center",
    marginTop: 30,
  },
});

export default LoginComponent;
