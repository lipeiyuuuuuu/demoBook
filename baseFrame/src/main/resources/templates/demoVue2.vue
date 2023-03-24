<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>vue.demo</title>
  <!-- 导入映射表 -->
  <script type="importmap">
  {
    "imports": {
      "vue": "https://unpkg.com/vue@3/dist/vue.esm-browser.js"
    }
  }
</script>
</head>
<body>



<div id="app">
  <div>{{ state.count }}</div>
  <button @click="increment">
    {{ state.count }}
  </button>
</div>

<script type="module">
  import { createApp ,reactive } from 'vue'

  export default {
    setup(){
      const state = reactive({ count: 0 })

      function increment() {
        state.count++
      }

      // 暴露 state 到模板
      return {
        state,
        increment
      }
    }
  }

  const app = createApp({
    data() {
      return {
      }
    }
  });
  /*挂在应用到#app DOM上*/
  app.mount('#app');


</script>

</body>
</html>