import { StyleSheet, View, Text } from "react-native";

const StatusComponent = () => {
  return (
    <View>
      <Text style={styles.container}>App works!</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    alignItems: "center",
    textAlign: "center",
    textAlignVertical: "center",
    fontSize: 20,
    width: 200,
    backgroundColor: "yellow",
  },
});

export default StatusComponent;
