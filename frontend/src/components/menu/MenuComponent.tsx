import { useNavigation } from "@react-navigation/native";
import { Button, StyleSheet, View } from "react-native";

const MenuComponent = () => {
  const navigation = useNavigation();
  return (
    <View style={styles.container}>
      <Button title="All" onPress={() => navigation.navigate("Home")}></Button>
      <Button title="Fav" onPress={() => navigation.navigate("Favourite")}></Button>
      <Button title="Button 3"></Button>
      <Button title="Button 4"></Button>
      <Button title="Button 5"></Button>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flexDirection: "row",
  },
});

export default MenuComponent;
