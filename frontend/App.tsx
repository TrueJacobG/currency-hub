import { StatusBar } from "expo-status-bar";
import { Box, NativeBaseProvider } from "native-base";
import { StyleSheet, Text, View } from "react-native";

export default function App() {
  return (
    <NativeBaseProvider>
      <View style={styles.container}>
        <Box>Hello world 2</Box>
      </View>
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
