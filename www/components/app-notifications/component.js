let nextId = 0;

export default class {
  onInput(input) {
    this.state = {
      notifications: input.notifications || []
    };
  }

  addAlerts(message) {
    const { state } = this;
    const notificationId = "notification" + nextId++;

    state.notifications = [
      {
        message: message,
        id: notificationId
      }
    ].concat(state.notifications);

    setTimeout(() => {
      this.removeNotification(notificationId);
    }, 3000);
  }

  removeNotification(notificationId) {
    const { state } = this;
    const notificationComponent = this.getComponent(notificationId);

    notificationComponent.fadeOut(() => {
      state.notifications = state.notifications.filter(
        notification => notification.id !== notificationId
      );
    });
  }
};
