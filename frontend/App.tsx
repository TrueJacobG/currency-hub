import { StatusBar } from "expo-status-bar";
import { Box, NativeBaseProvider } from "native-base";
import { StyleSheet, Text, View } from "react-native";
import BaseScreen from "./src/screen/BaseScreen";

export default function App() {
  return (
    <NativeBaseProvider>
      <BaseScreen />
    </NativeBaseProvider>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
});
