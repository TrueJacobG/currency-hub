import { useState } from "react";
import {
  StyleSheet,
  TextInput,
  View,
  Text,
} from "react-native";

const SigninComponents = () => {
  const [login, setLogin] = useState("");
  const [password, setPassword] = useState(""); //TODO
  const [name, setName] = useState("");
  const [surname, setSurnane] = useState("");

  return (
    <View>
      <Text style={styles.text}>Welcome to CurrencyHub</Text>
      <Text style={styles.textintro}>
        Please signin if you don't have an account:
      </Text>
      <TextInput
        style={styles.textinput}
        onChangeText={setName}
        placeholder={"Enter your name"}
      />
      <TextInput
        style={styles.textinput}
        onChangeText={setSurnane}
        placeholder={"Enter your surname"}
      />
      <TextInput
        style={styles.textinput}
        onChangeText={setLogin}
        placeholder={"Enter your email"}
      />
      <TextInput
        style={styles.textinput}
        onChangeText={setPassword}
        placeholder={"Enter your password"}
      />
      <TextInput
        style={styles.textinput}
        onChangeText={setPassword}
        placeholder={"Repeat your password"}
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
  textintro: {
    fontSize: 15,
    textAlign: "center",
    paddingBottom: 10,
    paddingTop: 10,
  },
});

export default SigninComponents;
