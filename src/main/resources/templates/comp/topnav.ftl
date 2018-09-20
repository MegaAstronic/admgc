  <div class="layui-header">
    <div class="layui-logo">${logo}</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">

      <#-- 递归  宏定义 -->
      <#macro dirTreeBuild curDir>
        <#list curDir.nodes as node>
        
          <#if (node.simpleClassName=="LinkNode") >
            <li class="layui-nav-item"><a href="${node.href}">${node.name}</a></li>
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
      <@dirTreeBuild curDir = topnavDir />
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item"><a href="">退出</a></li>
    </ul>
  </div>