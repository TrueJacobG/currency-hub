import { useState } from "react";
import { StyleSheet, Text, TextInput, View } from "react-native";
import { User } from "../../../type/User";

type Props = {
  user: User;
  setUser: any;
};

const SigninComponents = ({ user, setUser }: Props) => {
  const [password, setPassword] = useState("");
  const [repassword, setRepassword] = useState("");

  // TODO:
  // add show error
  // add validation
  // and
  // secure password

  const onChangeName = (name: string) => {
    setUser({ ...user, name: name });
  };

  const onChangeSurname = (surname: string) => {
    setUser({ ...user, surname: surname });
  };

  const onChangeEmail = (email: string) => {
    setUser({ ...user, email: email });
  };

  const onChangeAuthCode = (authCode: string) => {
    setUser({ ...user, authCode: authCode });
  };

  const onChangePassword = (password: string) => {
    setPassword(password);

    if (password !== repassword) {
      // console.error("Password and repeated password have to be the same!");
      return;
    }

    onChangeAuthCode(password);
  };

  const onChangeRePassword = (repassword: string) => {
    setRepassword(repassword);

    if (repassword !== password) {
      // console.error("Password and repeated password have to be the same!");
      return;
    }

    onChangeAuthCode(repassword);
  };

  return (
    <View>
      <Text style={styles.text}>Welcome to CurrencyHub</Text>
      <Text style={styles.textintro}>Please signin if you don't have an account:</Text>
      <TextInput style={styles.textinput} onChangeText={onChangeName} placeholder={"Enter your name"} />
      <TextInput style={styles.textinput} onChangeText={onChangeSurname} placeholder={"Enter your surname"} />
      <TextInput style={styles.textinput} onChangeText={onChangeEmail} placeholder={"Enter your email"} />
      <TextInput style={styles.textinput} onChangeText={onChangePassword} placeholder={"Enter your password"} />
      <TextInput style={styles.textinput} onChangeText={onChangeRePassword} placeholder={"Repeat your password"} />
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
  textintro: {
    fontSize: 15,
    textAlign: "center",
    paddingBottom: 10,
    paddingTop: 10,
  },
});

export default SigninComponents;
