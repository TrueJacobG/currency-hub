import { StyleSheet, View, Text } from "react-native";

const StatusComponent = () => {
  return (
    <View>
      <Text style={styles.container}>App works!</Text>
    </View>
  );
};

export default StatusComponent;

const styles = StyleSheet.create({
  container: {
    textAlign: "center",
    textAlignVertical: "center",
    fontSize: 18,
    width: 200,
    backgroundColor: "yellow",
  },
});
