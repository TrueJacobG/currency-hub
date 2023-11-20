import { useState } from "react";
import { StyleSheet, Text, TextInput, View } from "react-native";
import { User } from "../../../type/User";
import { MaterialCommunityIcons } from "@expo/vector-icons";

type Props = {
  user: User;
  setUser: any;
};

const SigninComponents = ({ user, setUser }: Props) => {
  const [password, setPassword] = useState("");
  const [repassword, setRepassword] = useState("");

  const [showPassword, setShowPassword] = useState(false);
  const [showRePassword, setShowRePassword] = useState(false);

  const toggleShowPassword = () => {
    setShowPassword(!showPassword);
  };
  const toggleShowRePassword = () => {
    setShowRePassword(!showRePassword);
  };

  // TODO:
  // add show error -- i don't know how to do it
  // add validation -- added
  // and
  // secure password -- added
  // layout //TODO

  const validateName = (name: string) => {
    let pattern = /^[A-Z]{1,1}+[a-z]{2,48}+$/;

    if (pattern.test(name) === false) {
      return false;
    } else {
      return true;
    }
  };

  const onChangeName = (name: string) => {
    let nameIsValid = validateName(name);
    if (!nameIsValid) {
      alert("Name is not valid.");
      return;
    } else {
      alert("Name is valid.");
      setUser({ ...user, name: name });
    }
  };

  const validateSurname = (surname: string) => {
    let pattern = /^[A-Z]{1,1}+[a-z]{2,48}+$/;

    if (pattern.test(surname) === false) {
      return false;
    } else {
      return true;
    }
  };

  const onChangeSurname = (surname: string) => {
    let surnameIsValid = validateSurname(surname);

    if (!surnameIsValid) {
      alert("Sutname is not valid.");
      return;
    } else {
      alert("Surname is valid.");
      setUser({ ...user, surname: surname });
    }
  };

  const validateNick = (nick: string) => {
    let pattern = /^[a-zA-Z0-9 ]{3,50}$/;
    if (pattern.test(nick) === false) {
      return false;
    } else {
      return true;
    }
  };

  const onChangeNick = (nick: string) => {
    let nickIsValid = validateNick(nick);

    if (!nickIsValid) {
      alert("Nick is not valid.");
      return;
    } else {
      alert("Nick is valid.");
      setUser({ ...user, nick: nick });
    }
  };

  const validateEmail = (email: string) => {
    let pattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w\w+)+$/;

    if (pattern.test(email) === false) {
      return false;
    } else {
      return true;
    }
  };

  const onChangeEmail = (email: string) => {
    let emailIsValid = validateEmail(email);

    if (!emailIsValid) {
      alert("Email is not valid.");
      return;
    } else {
      alert("Email is valid.");
      setUser({ ...user, email: email });
    }
  };

  const validateAuthCode = (authCode: string) => {
    if (authCode.length >= 10 && authCode.length <= 20) {
      return true;
    } else {
      return false;
    }
  };

  const onChangeAuthCode = (authCode: string) => {
    let authCodeIsValid = validateAuthCode(authCode);

    if (!authCodeIsValid) {
      alert("AuthCode is not valid.");
      return;
    } else {
      alert("AuthCode is valid.");
      setUser({ ...user, authCode: authCode });
    }
  };

  const onChangePassword = (password: string) => {
    setPassword(password);

    if (password !== repassword) {
      // alert("Your passwod anr repeated password should be the same!");
      // console.error("Password and repeated password have to be the same!");
      return;
    }

    onChangeAuthCode(password);
  };

  const onChangeRePassword = (repassword: string) => {
    setRepassword(repassword);

    if (repassword !== password) {
      // alert("Your passwod anr repeated password should be the same!");
      // console.error("Password and repeated password have to be the same!");
      return;
    }

    onChangeAuthCode(repassword);
  };

  return (
    <View>
      <Text style={styles.text}>Welcome to CurrencyHub</Text>
      <Text style={styles.textintro}>
        Please signin if you don't have an account:
      </Text>
      <TextInput
        style={styles.textinput}
        onChangeText={onChangeName}
        placeholder={"Enter your name"}
      />
      <TextInput
        style={styles.textinput}
        onChangeText={onChangeSurname}
        placeholder={"Enter your surname"}
      />
      <TextInput
        style={styles.textinput}
        onChangeText={onChangeNick}
        placeholder={"Enter your nick"}
      />
      <TextInput
        style={styles.textinput}
        onChangeText={onChangeEmail}
        placeholder={"Enter your email"}
      />
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
});

export default SigninComponents;
