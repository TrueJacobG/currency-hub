import { StyleSheet, Button, View } from "react-native";
import { Currency } from "../../type/Currency";

type Props = {
    currencies: Currency[];
};

const MenuComponent = () => {
    return (
        <View style={styles.container}>
            <Button title="Button 1"></Button>
            <Button title="Button 2"></Button>
            <Button title="Button 3"></Button>
            <Button title="Button 4"></Button>
            <Button title="Button 5"></Button>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flexDirection: "row"
    },
    button: {

    }
})

export default MenuComponent;
