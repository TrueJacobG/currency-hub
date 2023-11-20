import { StyleSheet, Text, TextInput, View } from "react-native";
import { LoginUser } from "../../../type/LoginUser";
import { useEffect, useState } from "react";
import { MaterialCommunityIcons } from "@expo/vector-icons";

type Props = {
  loginUser: LoginUser;
  setLoginUser: any;
};

const LoginComponent = ({ loginUser, setLoginUser }: Props) => {
  const [showPassword, setShowPassword] = useState(false);

  const toggleShowPassword = () => {
    setShowPassword(!showPassword);
  };
  // TODO:
  // add show error -- i don't know how to do it
  // add validation -- added simple validation
  // and
  // secure password -- password can be seen and hidden
  // layout

  const validateEmail = (email: string) => {
    let pattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w\w+)+$/;
    if (
      pattern.test(email) === false &&
      email.length <= 5 &&
      email.length >= 50
    ) {
      return false;
    } else {
      return true;
    }
  };

  const validAuthCode = (authCode: string) => {
    if (authCode.length >= 10 && authCode.length <= 20) {
      return true;
    } else {
      return false;
    }
  };

  const onChangeEmail = (email: string) => {
    let emailIsValid = validateEmail(email);

    if (!emailIsValid) {
      alert("Email is not valid.");
      return;
    } else {
      alert("Email is valid");
      setLoginUser({ ...loginUser, email: email });
    }
  };

  const onChangeAuthCode = (authCode: string) => {
    let authCodeIsValid = validAuthCode(authCode);

    if (!authCodeIsValid) {
      alert("Password is not valid.");
      return;
    } else {
      alert("Password is valid.");
      setLoginUser({ ...loginUser, authCode: authCode });
    }
  };

  return (
    <View style={styles.container}>
      <Text style={styles.text}>Login to your account:</Text>
      <TextInput
        style={styles.textinput}
        onChangeText={onChangeEmail}
        placeholder={"Enter your email"}
      />
      <TextInput
        style={styles.textinput}
        secureTextEntry={!showPassword}
        onChangeText={onChangeAuthCode}
        placeholder={"Enter your password"}
      />
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
