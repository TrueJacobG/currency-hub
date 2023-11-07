import { StyleSheet, View, Pressable, Text } from "react-native";
import LoginComponent from "../components/sign/login/LoginComponent";
import SigninComponents from "../components/sign/login/SigninComponents";
import { NavigationContainer } from "@react-navigation/native";
import {
  NativeStackScreenProps,
  createNativeStackNavigator,
} from "@react-navigation/native-stack";
import { ScreenNaviagtion } from "../type/ScreenNavigation";

type Props = NativeStackScreenProps<ScreenNaviagtion, "Log">;

const LogScreen = ({ navigation }: Props) => {
  return (
    <View>
      <LoginComponent />
      <Pressable
        style={() => [styles.button]}
        onPress={() => navigation.navigate("Home")}
      >
        <Text style={[styles.text]}>Login</Text>
      </Pressable>
      <Text style={styles.textintro}>
        If you don't have an account please register
      </Text>
      {/*Notka: nad wyglądem przycisków i i funkcji jeszcze popracuję. */}
      <Pressable
        style={() => [styles.button]}
        onPress={() => navigation.navigate("Sign")}
      >
        <Text style={[styles.text]}>Register</Text>
      </Pressable>
      {/*Notka: Nie wiem czy przypadkiem przyciski typu: (Login, Signin) nie powinny sie znaleźć w plikach LoginComponencts i SigninComponents ponieważ jakoś trzeba za pomocą wciśnięcia przycisku przekazać dane do autorization i je przetworzyć oraz przekazać dalej do backendu. */}
    </View>
  );
};

export default LogScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
    textAlignVertical: "center",
  },
  text: {
    fontSize: 30,
    textAlign: "center",
    marginTop: 10,
    marginBottom: 10,
  },
  button: {
    borderWidth: 1,
    backgroundColor: "pink",
  },
  textintro: {
    textAlign: "center",
    marginBottom: 10,
    marginTop: 10,
  },
});
