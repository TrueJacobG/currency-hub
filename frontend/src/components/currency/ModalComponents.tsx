import React from 'react';
import { View, Text, Button, Modal } from 'react-native';

interface ModalComponentProps {
  isVisible: boolean;
  toggleModal: () => void;
}

const ModalComponent: React.FC<ModalComponentProps> = ({ isVisible, toggleModal }) => {
  return (
    <Modal
      animationType="slide"
      transparent={true}
      visible={isVisible}
      onRequestClose={toggleModal}
    >
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <View style={{ backgroundColor: 'white', padding: 20, borderRadius: 10 }}>
          <Text>Your Modal Content</Text>
          <Button title="Close Modal" onPress={toggleModal} />
        </View>
      </View>
    </Modal>
  );
};

export default ModalComponent;