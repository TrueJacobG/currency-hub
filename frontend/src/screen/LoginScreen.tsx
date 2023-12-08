import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { useAtom } from "jotai";
import { useState } from "react";
import { Pressable, StyleSheet, Text, View } from "react-native";
import LoginComponent from "../components/auth/LoginComponent";
import { loggedUserAtom } from "../jotai/loggedUserAtom";
import { AuthorizationService } from "../services/AuthorizationService";
import { LoginUser } from "../type/LoginUser";
import { ScreenNaviagtion } from "../type/ScreenNavigation";

type Props = NativeStackScreenProps<ScreenNaviagtion, "Log">;

const LogScreen = ({ navigation }: Props) => {
  const authService = new AuthorizationService();

  const [loggedUser, setLoggedUser] = useAtom(loggedUserAtom);

  const [loginUser, setLoginUser] = useState<LoginUser>({
    email: "",
    authCode: "",
  });

  const onLoginPress = async () => {
    if (typeof loginUser === "undefined") {
      console.error("LoginUser is undefined!");
      return;
    }

    const result = await authService.loginUser(loginUser);

    if (result.status == "OK") {
      setLoggedUser({ ...loggedUser, ...loginUser });
      setLoginUser({ email: "", authCode: "" });
      navigation.navigate("Home");
    }
  };

  return (
    <View>
      <LoginComponent loginUser={loginUser} setLoginUser={setLoginUser} />
      <Pressable style={() => [styles.button]} onPress={onLoginPress}>
        <Text style={[styles.text]}>Login</Text>
      </Pressable>
      <Text style={styles.textintro}>
        If you do not have an account please register
      </Text>
      <Pressable
        style={() => [styles.button]}
        onPress={() => navigation.navigate("Sign")}
      >
        <Text style={[styles.text]}>Register</Text>
      </Pressable>
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
