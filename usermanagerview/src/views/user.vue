<template>
  <div class="wrapper">
    <div class="d-flex justify-content-center">
      <h1 class="display-6">UserManager &nbsp;&nbsp;共{{ pageBean.totalCount }}条数据 · {{ pageBean.totalPage }}页</h1>
    </div>
    <div class="d-flex justify-content-center">
      <div class="tables">
        <table class="table table-striped table-hover  border border-1 shadow p-2 mb-5 bg-body-tertiary rounded">
          <thead>
          <tr>
            <th scope="col">编号：</th>
            <th scope="col">姓名：</th>
            <th scope="col">Email：</th>
            <th scope="col">生日：</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="user in pageBean.userList" :key="user.id">
            <th scope="row">{{ user.id }}</th>
            <td>{{ user.name }}</td>
            <td>{{ user.email }}</td>
            <td>{{ user.birthday }}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>

    <!--    共{{ pageBean.totalCount }}数据-->
    <!--    共{{ pageBean.totalPage }}页-->
    <!--    <br/>-->

    <!--    跳转到-->
    <!--    <select @change="goPage(pageBean.pageSize,$event.target.value)">-->
    <!--      <option v-for="i in pageBean.totalPage" :key="i" :value=i>{{ i }}</option>-->
    <!--    </select>-->
    <!--    页-->
    <!--    <br/>-->

    <!--    每页显示条数-->
    <!--    <select @change="goPage($event.target.value,pageBean.currentPage)">-->
    <!--      <option value="5">5</option>-->
    <!--      <option value="10">10</option>-->
    <!--      <option value="20">20</option>-->
    <!--    </select>-->
    <!--    <br/>-->

    <!--    <a href="javascript:void(0)" @click="goPage(pageBean.pageSize,1)">首页</a>-->
    <!--    <a href="javascript:void(0)" @click="goPage(pageBean.pageSize,pageBean.currentPage-1)">上一页</a>-->
    <!--    <a href="javascript:void(0)"-->
    <!--       v-for="page in displayedPages"-->
    <!--       :key="page"-->
    <!--       @click="goPage(pageBean.pageSize,page)"-->
    <!--       :class="{active:page===pageBean.currentPage}"-->
    <!--    >{{page}}</a>-->
    <!--    <a href="javascript:void(0)" @click="goPage(pageBean.pageSize,pageBean.currentPage+1)">下一页</a>-->
    <!--    <a href="javascript:void(0)" @click="goPage(pageBean.pageSize,pageBean.totalPage)">尾页</a>-->

    <div class="d-flex justify-content-center">
      <div class="selector">
        <h4>跳转到第</h4>
        <div class="selection">
          <select class="form-select form-select-sm" aria-label=".form-select-sm example"
                  @change="goPage(pageBean.pageSize,$event.target.value)">
            <option v-for="i in pageBean.totalPage" :key="i" :value=i>{{ i }}</option>
          </select>
        </div>
        <h4>页</h4>
      </div>
      <div class="selector">
        <h4>每页显示</h4>
        <div class="selection">
          <select class="form-select form-select-sm" aria-label=".form-select-sm example"
                  @change="goPage($event.target.value,pageBean.currentPage)">
            <option value="5">5</option>
            <option value="10">10</option>
            <option value="20">20</option>
          </select>
        </div>
        <h4>条</h4>
      </div>
    </div>

    <!--页码栏-->
    <nav aria-label="...">
      <ul class="pagination justify-content-center ">
        <li class="page-item"><a class="page-link" href="javascript:void(0)"
                                 @click="goPage(pageBean.pageSize,1)">首页</a></li>
        <li class="page-item">
          <a class="page-link"
             href="javascript:void(0)"
             @click="goPage(pageBean.pageSize,pageBean.currentPage-1)"
          >上一页</a>
        </li>

        <li v-for="page in displayedPages"
            :key="page"
            :class="active(page)">
          <a class="page-link"
             @click="goPage(pageBean.pageSize,page)"
             href="javascript:void(0)"
          >{{ page }}</a>
        </li>

        <li class="page-item">
          <a class="page-link"
             href="javascript:void(0)"
             @click="goPage(pageBean.pageSize,pageBean.currentPage+1)"
          >下一页</a></li>
        <li class="page-item"><a class="page-link" href="javascript:void(0)"
                                 @click="goPage(pageBean.pageSize,pageBean.totalPage)">尾页</a></li>
      </ul>
    </nav>

  </div>
</template>

<script setup>

import axios from "axios";
import {computed, ref} from "vue";

const pageBean = ref({
  pageSize: 5,
  currentPage: 1,
  totalCount: 0,
  totalPage: 2,
  userList: []
});

function goPage(page, current) {
  if (current <= 0) {
    current = 1;
  } else if (current > pageBean.value.totalPage) {
    current = pageBean.value.totalPage;
  }
  axios.get("http://localhost:8090/user/findAll?pageSize=" + page + "&currentPage=" + current)
      .then((resp) => {
        // console.log(resp)
        // pageBean.value.userList=resp.data;
        pageBean.value = resp.data;
      })
}

goPage(pageBean.value.pageSize, 1);

const displayedPages = computed(() => {
  const current = pageBean.value.currentPage;
  const total = pageBean.value.totalPage;
  const displayed = [];
  if (current <= 5) {
    for (let i = 1; i <= 10; i++) {
      displayed.push(i);
    }
  } else if (current >= total - 5) {
    for (let i = total - 9; i <= total; i++) {
      displayed.push(i);
    }
  } else {
    for (let i = Math.max(1, current - 4); i <= Math.min(total, current + 5); i++) {
      displayed.push(i)
    }
  }
  return displayed;
})

function active(page) {
  if (page === pageBean.value.currentPage) {
    return 'page-item active'
  } else {
    return 'page-item'
  }
}
</script>

<style scoped>
.wrapper {
  margin: 5vw 10vw;
}

.display-6 {
  margin-bottom: 2vw;
}

.tables {
  width: 80%;
  justify-content: center;
}

.selector {
  display: flex;
  margin: 0.2vw 1vw 2vw;
}

.selection {
  width: 4vw;
  margin: 0 5px;
}
</style>