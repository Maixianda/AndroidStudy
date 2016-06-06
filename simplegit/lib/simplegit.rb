# a super simple example class to use git in ruby
#修改来作为分支的测试
class SimpleGit
  
  def initialize(git_dir = '.')
    @git_dir = File.expand_path(git_dir)
  end
  
  def show(treeish = 'master')
    command("git show #{treeish}")
  end

  private
  
    def command(git_cmd)
      Dir.chdir(@git_dir) do
        return `#{git_cmd} 2>&1`.chomp
      end
    end
  
end
