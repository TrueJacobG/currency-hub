import { StyleSheet, View, Pressable, Text } from "react-native";
import SigninComponents from "../components/sign/login/SigninComponents";
import { ScreenNaviagtion } from "../type/ScreenNavigation";
import { NativeStackScreenProps } from "@react-navigation/native-stack";

type Props = NativeStackScreenProps<ScreenNaviagtion, "Sign">;

const SignScreen = ({ navigation }: Props) => {
  return (
    <View>
      <SigninComponents />
      <Pressable
        style={styles.button}
        onPress={() => navigation.navigate("Home")}
      >
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
