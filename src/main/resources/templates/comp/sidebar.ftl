  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        

          <#-- 递归  宏定义 -->
          <#macro dirTreeBuild curDir>
            <#list curDir.nodes as node>
            
              <#if (node.simpleClassName=="LinkNode") >
              	<li class="layui-nav-item">
                  <li class="layui-nav-item"><a href="${node.href}">${node.name}</a></li>
              	</li>
              </#if>
            
            
              <#if (node.simpleClassName=="DirNode") >
              	<li class="layui-nav-item">
                  <a class="" href="javascript:;">${node.name}</a>
                  <dl class="layui-nav-child">
                    <#list node.nodes as subNode>
                      <dd><a href="${subNode.href}">${subNode.name}</a></dd>
                    </#list>
                  </dl>
                </li>
              </#if>
            
            </#list>
          </#macro>
          <@dirTreeBuild curDir = sidebarDir />
      </ul>
    </div>
  </div>

  <script>
  //功能是让跟第一个与地址相吻合的a标签被选中
    layui.use('jquery',function(){
      var $ = layui.$
      var processURL = function(path) {
        var path1 = path.indexOf("?");
        var path2 = path.indexOf("#");
        if (path1 == -1) {
          path1 = path.length;
        }
        if (path2 == -1) {
          path2 = path.length;
        }
        var endIndex = Math.min(path1, path2);
        var url = path.substring(0, endIndex);
        return url;
      };
      var targetURL = processURL(window.location.href);
      var flag = false;//代表找到了
      $(".layui-side").find("a").each(function(){
        if (flag == false) {
          if(processURL($(this).prop("href")) == targetURL){
            flag = true;
            $(this).parent().addClass("layui-this");
            var navitem = $(this).parent().parent().parent();
            if(navitem.hasClass("layui-nav-item")){
              navitem.addClass("layui-nav-itemed");
            }
          }
        }
      })
    })
  </script>