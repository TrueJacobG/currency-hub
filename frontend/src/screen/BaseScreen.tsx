import { StyleSheet, View, Text, Button } from "react-native";
import StatusComponent from "../components/status/StatusComponent";
import { NavigationContainer } from "@react-navigation/native";
import { NativeStackScreenProps, createNativeStackNavigator } from "@react-navigation/native-stack";
import { ScreenNaviagtion } from "../type/ScreenNavigation";

type Props = NativeStackScreenProps<ScreenNaviagtion, "Home">;

const BaseScreen = ({ navigation }: Props) => {
  return (
    <View>
      {/*<StatusComponent />*/}
      <Text style={styles.textintro}>Welcome to CurrencyHub!</Text>
      <Text style={styles.text}>This is not your money any more</Text>
      <Button
        title="Login"
        onPress={() => navigation.navigate("Log")}
        color="#841584"
      />
      <Button
        title="Sigin"
        onPress={() => navigation.navigate("Sign")}
        color="#841584"
      />
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
