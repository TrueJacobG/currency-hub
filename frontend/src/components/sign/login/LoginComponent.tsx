import { useState } from "react";
import {
  StyleSheet,
  TextInput,
  View,
  Text,
} from "react-native";
import passwordEncoder from "../../../utils/passwordEncoder";

const LoginComponent = () => {
  //const authorizationService = new AuthorizationService();
  const [login, setLogin] = useState("");
  const [password, setPassword] = useState("");

  const securePassword = async (password: string) => {
    //console.log(password);  //TODO
    /*let securedPassword: string =await passwordEncoder(password);*/
    //console.error(setPassword);
    setPassword(password);
  };
  return (
    <View style={styles.container}>
      <Text style={styles.text}>Login to your account:</Text>
      <TextInput
        style={styles.textinput}
        onChangeText={setLogin}
        placeholder={"Enter your email"}
      />
      <TextInput
        style={styles.textinput}
        onChangeText={securePassword}
        placeholder={"Enter your password"}
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
