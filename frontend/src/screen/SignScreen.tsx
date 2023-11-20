import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { useAtom } from "jotai";
import { useState } from "react";
import { Pressable, StyleSheet, Text, View } from "react-native";
import SigninComponents from "../components/sign/login/SigninComponents";
import { loggedUserAtom } from "../jotai/loggedUserAtom";
import { AuthorizationService } from "../services/AuthorizationService";
import { ScreenNaviagtion } from "../type/ScreenNavigation";
import { User } from "../type/User";

type Props = NativeStackScreenProps<ScreenNaviagtion, "Sign">;

const SignScreen = ({ navigation }: Props) => {
  const authService = new AuthorizationService();

  const [loggedUser, setLoggedUser] = useAtom(loggedUserAtom);

  const [user, setUser] = useState<User>({
    name: "",
    surname: "",
    nick: "",
    email: "",
    authCode: "",
  });

  const onSignInPress = async () => {
    if (typeof user === "undefined") {
      console.error("User is undefined!");
      return;
    }

    const result = await authService.registerUser(user);

    if (result.message === "ok" && result.status == "ACCEPTED") {
      setLoggedUser(user);
      navigation.navigate("Home");
    }
  };

  return (
    <View>
      <SigninComponents user={user} setUser={setUser} />
      <Pressable style={styles.button} onPress={onSignInPress}>
        <Text style={[styles.textbut]}>Signin</Text>
      </Pressable>
      <Text style={styles.textintro}>
        If you already have an account please login:
      </Text>
      <Pressable
        style={() => [styles.button]}
        onPress={() => navigation.navigate("Log")}
      >
        <Text style={[styles.textbut]}>Login</Text>
      </Pressable>
    </View>
  );
};

export default SignScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
  text: {
    color: "black",
  },
  button: {
    borderWidth: 1,
    backgroundColor: "pink",
  },
  textbut: {
    textAlign: "center",
    fontSize: 18,
    marginBottom: 10,
    marginTop: 10,
  },
  textintro: {
    fontSize: 15,
    textAlign: "center",
    paddingBottom: 10,
    paddingTop: 10,
  },
});
