import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { useAtom } from "jotai";
import { StyleSheet, Text, View } from "react-native";
import { loggedUserAtom } from "../jotai/loggedUserAtom";
import { ScreenNaviagtion } from "../type/ScreenNavigation";
import { User } from "../type/User";

type Props = NativeStackScreenProps<ScreenNaviagtion, "Home">;

const BaseScreen = ({ navigation }: Props) => {
  const [loggedUser, setLoggedUser] = useAtom<User>(loggedUserAtom);

  return (
    <View>
      <Text style={styles.textintro}>Welcome to CurrencyHub!</Text>
      <Text style={styles.text}>This is not your money any more</Text>
      <View>
        <Text>Email: {loggedUser.email}</Text>
      </View>
    </View>
  );
};

export default BaseScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
  textintro: {
    textAlign: "center",
    fontSize: 40,
    marginTop: 20,
  },
  text: {
    textAlign: "center",
    fontSize: 10,
    marginBottom: 40,
  },
});
