import { StyleSheet, Text, View } from "react-native";
import BaseScreen from "./src/screen/BaseScreen";

const App = () => {
  return (
    <View style={styles.container}>
      <BaseScreen />
    </View>
  );
};

export default App;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
});
