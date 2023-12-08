import { useNavigation } from "@react-navigation/native";
import { Button, StyleSheet, View } from "react-native";

const MenuComponent = () => {
  const navigation = useNavigation();
  return (
    <View style={styles.container}>
      <Button title="All" onPress={() => navigation.navigate("Home")}></Button>
      <Button title="Fav" onPress={() => navigation.navigate("Favourite")}></Button>
      <Button title="Wallet" onPress={() => navigation.navigate("Wallet")}></Button>
      <Button title="Alerts" onPress={() => navigation.navigate("Alert")}></Button>
      <Button title="Profile" onPress={() => navigation.navigate("User")}></Button>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flexDirection: "row",
  },
});

export default MenuComponent;
