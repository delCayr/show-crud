<template>
  <div id="app">
    <HelloWorld msg="Welcome to Your Vue.js App" />
  </div>
</template>

<script>
import HelloWorld from "./components/HelloWorld copy.vue";

export default {
  name: "App",
  components: {
    HelloWorld,
  },
};
</script>

<style>
body,
html {
  margin: 0;
  padding: 0;
}
#app {
  width: 100vw;
  height: 100vh;
}
</style>
